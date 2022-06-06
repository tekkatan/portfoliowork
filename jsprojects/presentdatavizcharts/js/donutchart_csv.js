function donutchart(){
    // d3 code goes here
    const margin=10;
    const width=400;
    const height=400;
    // create the svg figure with background
    const svg=d3.select("#donutchart")
                .attr("width",width)
                .attr("height",height)
                .style("background","#202529");
                
    const radius=Math.min(width,height)/2;
    // add a colorscheme 
    // If you want to use a custom colorscheme
    // simply use: 
    // var color=d3.scaleOrdinal(["#colorcode1""#colorcode2","#colorcode3"])
    var color=d3.scaleOrdinal(d3.schemeDark2);
    // append the title to the figure
    svg.append("text").attr("transform","translate(-60,-15)")
        .attr("x",80)
        .attr("y",50)
        .attr("class","title")
        .text("Sales per agent in % - May 2023");

    d3.csv("../data/sales.csv").then(function(data){
       // the data retrieved from the csv
        var pie=d3.pie()
            .sort(null)
            .value(function(d){return d.percent;})
        // create pie segments
        var segments=d3.arc()
            .innerRadius(50)
            .outerRadius(110)
            .padAngle(.05)
            .padRadius(5);
        // create a new group
        var g=svg.append("g")
            .attr("transform","translate("+width/2+","+height/2+")");
        // add the pie data to the segements, add them to the 
        // newly created group
        // link them to a class called "arc"
        var arcs=g.selectAll("segments")
            .data(pie(data))
            .enter().append("g")
            .attr("class","arc");
        // add the earlier created colorscheme to to segments
        var g2=g.selectAll(".arc2")
            .data(pie(data))
            .enter().append("g")
            .attr("class","arc");
        arcs.append("path")
            .attr("d",segments)
            .style("stroke","none")
            .attr("fill", function(d,i){
                return color(i);
            })// animate their apperance with a slice in (pun intended)
            .transition()
            .ease(d3.easeLinear)
            .duration(500)
            .style("opacity","1")
            .attrTween("d",function(d){
                var i=d3.interpolate(d.startAngle,d.endAngle);
                return function(f){
                    d.endAngle=i(f);
                    return segments(d);
                }
            });
            // Add text to pie segments
            // with a delay so it will load after the segments
            // are loaded
        g2.append("text")
            .classed("inside",true)
            .attr("transform",function(d){
                var r=segments.centroid(d);
                return "translate("+r[0]+","+r[1]+")";
            })
            .text(function(d){return +d.data.percent;})
            .transition()
            .ease(d3.easeLinear)
            .duration(500).delay(500)
            .style("opacity","1");

        //Call mouseover and mouseout animation
        arcs
        .on("mouseover", function(d){
            var arcOver=d3.arc().innerRadius(0)
            .outerRadius(radius-190);
            d3.select(this)
                .attr("class","hoverpie")
                .transition()
                .ease(d3.easeBounce)
                .duration(1000)
                .attr("transform",
                function(d){ return `translate(${arcOver.centroid(d)})`})
        })
        .on("mouseout", function(){
            d3.select(this)
                .transition()
                .duration(500)
                .attr("transform","translate(0,0)")})
            .transition()
            .ease(d3.easeBounce)
            .duration(2000)
            .attrTween("d",tweenPie);
            // making sure the text returns to its original place 
            arcs.select("text")
            .classed("inside",true)
            .attr("transform",function(d){
                var r=segments.centroid(d);
                return "translate("+r[0]+","+r[1]+")";
            })
            .text(function(d){return +d.data.percent;})
            .transition()
            .ease(d3.easeLinear)
            .duration(500).delay(500)
            .style("opacity","1");


        // Create a legend
        var legends=svg.append("g")
                       .attr("transform","translate(10,100)")
                       .selectAll(".legends").data(data);
        var legend=legends.enter().append("g").classed("legends",true)
        .attr("transform",function(d,i){
            return "translate(0,"+(i+1)*30 +")";
        });
        legend.append("rect").attr("width",20).attr("height",20)
        .attr("fill",function(d,i){
            return color(i);});
        legend.append("text").classed("label",true).text(function(d){return d.agent;})
            .attr("fill",function(d,i){
                return color(i);})
            .attr("x",30)
            .attr("y",20); 
   })
   
}
function tweenPie(p){
    var svg=d3.select("svg"),
    margin=10,
    width=svg.attr("width")-margin,
    height=svg.attr("height")-margin,
    radius=Math.min(width,height)/2;
    var arc=d3.arc()
            .innerRadius(0)
            .outerRadius(radius-40);
    p.innerRadius=0;
    var i=d3.interpolate({startAngle:0, endAngle:0},p);
    return function(t){return arc(i(t));};
}
donutchart();