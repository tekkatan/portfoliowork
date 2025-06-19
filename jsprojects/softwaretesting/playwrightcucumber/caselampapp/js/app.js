const light=document.getElementById("light");
const lightSwitch=document.getElementById("light-switch");

lightSwitch.addEventListener("click",()=>{
    if(light.classList.contains("light-three")){
        light.classList.remove("light-three");
        light.classList.add("light-two");
    }
    else if(light.classList.contains("light-two")){
        light.classList.remove("light-two");
        light.classList.add("light-one");
    }
    else if(light.classList.contains("light-one")){
        light.classList.remove("light-one");
    }else{
        light.classList.add("light-three");
    }
    
})