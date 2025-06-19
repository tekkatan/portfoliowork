const {chromium, expect}=require("@playwright/test");
exports.LampPage=class LampPage{
    // Constructor for Page Object Model of the lamppage
    constructor(page,expect){
        this.page=page;
        this.light=this.page.locator("#light");
        this.lightSwitch=this.page.locator("#light-switch");
    }

    // Async function for goto page of lightswitch application
    async goToLampPage(){
        await this.page.goto("http://127.0.0.1:3000/index.html");
    }

    // Async function to click the lightswitch
    async clickLightSwitch(){
        await this.lightSwitch.click();
    }

    // Async function to verify the expected value of the light with the actual value
    async verifyLight_3_is_on(content){
        await expect(content).toEqual("light-three");
        await expect(this.light).toContainClass(content);
    }
    // Async function for taking screenshots and save them in a custom folder with timestamp
    async takeScreenshot(textStep){
        const timestamp=new Date().toISOString().replace(/[:.]/g,'-');
        const screenShotPath=`./tests/tmp/screenshots/${textStep}_${timestamp}.png`;
        await this.page.screenshot({path:screenShotPath});
    }
};