const{Given,When, Then,Before, BeforeStep, BeforeAll, After, AfterAll,AfterStep}=require("@cucumber/cucumber");
const{chromium,context}=require("@playwright/test");
const{LampPage}=require("./LampPage.spec");

let browser
let page
let lampPage;

Before(async()=> {
     console.log("Starting browser")
     browser=await chromium.launch({headless:true});
     const context=await browser.newContext();
     page=await context.newPage();
     lampPage=new LampPage(page);
})
BeforeStep(async()=> {
     console.log("Before: Start step")
})
BeforeAll(async()=> {
     // suite level
     console.log("TestSuite Lightswitch - Acceptance Test - Start")
})
After(async()=> {
     // close actions
     console.log("Closing page and browser")
     await page.close()
     await browser.close();
})
AfterStep(async()=> {
     console.log("After: Take screenshot of current step")
     const AfterString="After"
     await lampPage.takeScreenshot(AfterString);
})
AfterAll(async()=> {
     console.log("TestSuite Lightswitch - Acceptance Test - End")
})

Given('User navigates to lightswitchpage', async function(){
     console.log("Given: User navigates to lightswitchpage")
     await lampPage.goToLampPage();
});
When('I click the light switch',async function(){
     console.log("When: I click the light switch")
     lampPage.clickLightSwitch();
});
Then('I should see the light turned on at intensity 3',async function(){
     console.log('Then: I should see the light turned on at intensity 3')
     await lampPage.verifyLight_3_is_on("light-three");   
});

