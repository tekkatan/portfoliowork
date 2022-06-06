function barchart(){
    // Init SVG
    var svg=d3.select("#barchart"),
    margin=200,
    width=svg.attr("width")-margin,
    height=svg.attr("height")-margin;
    // delay animation loading svg
    d3.select("#barchart").transition()
    .ease(d3.easeLinear)
    .duration(1000)
    .style("opacity","1")
    .delay(function(d,i){return i*50});

    // add a title to Chart
    svg.append("text").attr("transform","translate(-60,-15)")
                .attr("x",80)
                .attr("y",50)
                .attr("class","glowaxis")
                .attr("font-size","18px")
                .text("Stock Price Luxury Carrots");

    var xScale=d3.scaleBand().range([0,width]).padding(0.4);
    var yScale=d3.scaleLinear().range([height,0]);
    var g=svg.append("g").attr("transform","translate("+100+","+100+")");

    // Importing data from csv
    d3.csv("../data/stock_values_carrots.csv").then(function(data){
        xScale.domain(data.map(function(d){
            return d.year;
        }))
        yScale.domain([0,d3.max(data,function(d){
            return d.value;
        })])
        // axis placing
        g.append("g")
            .attr("transform","translate(0,"+height+")")
            .attr("class","glowaxis")
            .call(d3.axisBottom(xScale))
            // adding text label on X-axis
            .append("text")
            .attr("transform","translate(100,50)")
            .attr("x",10)
            .attr("dx","1em")
            .attr("text-anchor","middle")
            .attr("font-size","16px")
            .text("Years");
        g.append("g")
            .attr("class","glowaxis")
            .call(d3.axisLeft(yScale)
                .tickFormat(function(d){
                //convert currency to EUR
                return d.toLocaleString('nl-NL',{style:'currency',currency:"EUR"});
                }).ticks(10))
            // adding text label on Y-axis
            .append("text")
            //.attr("transform","translate(50,10)")
            .attr("transform","rotate(-90)")
            .attr("y",10)
            .attr("dy","-5em")
            .attr("text-anchor", "end")
            .attr("font-size","16px")
            
            //.attr("stroke","aquamarine")
            .text("In EUR");
        
        // bar creation
        g.selectAll(".bar")
                .data(data)
                .enter().append("rect")
                .attr("class","bar")
                //Call mouseover and mouseout
                .on("mouseover", onMouseOver)
                .on("mouseout", onMouseOut)
                //
                .attr("x",function(d){return xScale(d.year);})
                .attr("y",height)
                .attr("width",xScale.bandwidth())
                // height is 0, so it can later be animated 
                // to its actual length based on d.value from
                // the csv data file
                .attr("height",0)
                .attr("fill","#1b9e77");
                // adds delay animation with easeLinear
                // opacity is 0 in css, here
                // it animates from 0 to 1
                // height animates from bottom to top
          g.selectAll(".bar")      
                .transition()
                .ease(d3.easeLinear)
                .duration(500)
                .style("opacity","1")
                .attr("y",(d,i)=>yScale(d.value))
                .attr("height",function(d){return height-yScale(d.value);})
                .delay(function(d,i){return i*50});
    });
    // Mouseover event handler
    function onMouseOver(d,i){
        // get bar x,y coordinates, then argument for tooltip
        var xPos=parseFloat(d3.select(this).attr("x"))+xScale.bandwidth()/2;
        var yPos=parseFloat(d3.select(this).attr("y"))/2+height/2;
        // update tooltip's position and value
        d3.select("#tooltip")
            .style("left",xPos+10+"px")
            .style("top",yPos+140+"px")
            .select("#value").text(i.year+", "+i.value)
        d3.select("#tooltip").classed("hidden",false);

        d3.select(this).attr("class","highlight")
        d3.select(this)
            .transition()// I want to add animation here
            .duration(500)
            .attr("width", xScale.bandwidth()+5)
            .attr("y",function(d){return yScale(d.value)-10;})
            .attr("height",function(d){return height-yScale(d.value)+10;});
    }
    // Mouseout event handler
    function onMouseOut(d,i){
        d3.select(this).attr("class","bar")
        d3.select(this)
                .transition()
                .duration(500)
                .attr("width",xScale.bandwidth())
                .attr("y",function(d){return yScale(d.value);})
                .attr("height",function(d){return height-yScale(d.value);})
        d3.select("#tooltip").classed("hidden",true);
    }
}
barchart();
