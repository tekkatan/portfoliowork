function bubbleChart(){
    // d3 code goes here
    const width = 400;
    const height = 400;

    // location to centre the bubbles
    const centre = { x: width/2, y: height/2 };

    // strength to apply to position forces
    const forceStrength = 0.03;

    // will be set in createNodes and chart functions
    let svg = null;
    let bubbles = null;
    let labels = null;
    let nodes = [];

    // charge is dependent on size of the bubble, 
    // so bigger towards the middle
    function charge(d) {
        return Math.pow(d.radius, 2.0) * 0.01
    }

    // create a force simulation and add forces
    const simulation = d3.forceSimulation()
        .force('charge', d3.forceManyBody().strength(charge))
        .force('x', d3.forceX().strength(forceStrength).x(centre.x))
        .force('y', d3.forceY().strength(forceStrength).y(centre.y))
        .force('collision', d3.forceCollide().radius(d => d.radius + 1));

    simulation.stop();
    //set up colour scale
    // below the domains are the agents from the csv file
    const fillColour = d3.scaleOrdinal()
  	    .domain(["Frans", "Willy", "Emma", "Joseph","Quincy","Tygo"])
  	    .range(["#1b9e77", "#d95f02", "#7570b3", "#e7298a","#66a61e","#e6ab02"]);

    // data manipulation function 
    function createNodes(rawData){
        const maxSize=d3.max(rawData, d => +d.percent);

        const radiusScale=d3.scaleSqrt()
            .domain([0, maxSize])
            .range([0, 80])
        
        //convert raw data into node data
        const myNodes=rawData.map(d=>({
            ...d,
            radius: radiusScale(+d.percent),
            size: +d.size,
            x: Math.random()+400,
            y: Math.random()*400
        }))
        return myNodes;
    }
    
    // main entry point to bubblechart, returns
    // prepares rawData for dataviz
    // adds svg element 
    let chart=function chart(selector,rawData){
        // convert raw data into node data
        nodes=createNodes(rawData);

        // create svg element inside provided selector
        // #bubblechart is here the selector
        svg=d3.select(selector)
            .append('svg')
            .attr('width',width)
            .attr('height',height)
        // append the title to the figure
        svg.append("text").attr("transform","translate(-60,-15)")
            .attr("x",80)
            .attr("y",50)
            .attr("class","title")
            .attr("font-size","18px")
            .text("Ranking agent Sales - May 2023");
        
        //bind nodes data to circle elements
        const elements=svg.selectAll('.bubble')
            .data(nodes,d=>d.agent)
            .enter()
            .append('g')
        // assign value color
        bubbles=elements
            .append('circle')
            .classed('bubble',true)
            .attr('r',d=>d.radius)
            .attr('fill', d=>fillColour(d.agent))
        
        // labels
        // assign value from csv
        labels=elements
            .append('text')
            .attr('dy', '.3em')
            .style('text-anchor','middle')
            .style('font-size',14)
            .attr("fill","white")
            .text(d=>d.agent);
        // set simulation nodes to new created nodes
        // array simulation starts running automatically
        simulation.nodes(nodes)
            .on('tick',ticked)
            .restart();
    }
    // callback function call after every tick of the force 
    // simulation
    // repositioning of circle based on the current x and y value
    function ticked(){
        bubbles
            .attr('cx',d=>d.x)
            .attr('cy',d=>d.y)
        
        labels
            .attr('x',d=>d.x)
            .attr('y',d=>d.y)
    }
    
    // return chart function from closure
    return chart;
    
}
    // new bubble chart instance
    let myBubbleChart=bubbleChart();

    //function called once promise is resolved and data
    // is loaded from csv
    function display(data){
        // #bubblechart is here the selector
        // data is the data from the csv
        myBubbleChart('#bubblechart',data);
    }

    // load data
    d3.csv('../data/sales.csv').then(display);