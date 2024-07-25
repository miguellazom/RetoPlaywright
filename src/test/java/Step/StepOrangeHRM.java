package Step;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class StepOrangeHRM {
    private final Page page;
    private final Locator userName;
    private final Locator password;
    private final Locator loginButton;
    private final Locator addUser;
    private final Locator firstName;
    private final Locator middleName;
    private final Locator lasName;
    private final Locator regiterSubmit;
    private final Locator pim;

    private final Locator otherId;
    private final Locator licenseNum;
    private final Locator licenseDate;
    private final Locator checkBoxMale;
    private final Locator savePersonDetails;
    private final Locator filter;
    private final Locator searchButton;
    private final Locator listUser;
    private String name;



    public StepOrangeHRM(Page page) {
        this.page = page;
        this.userName = page.getByPlaceholder("Username");
        this.password = page.getByPlaceholder("Password");
        this.loginButton = page.locator("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div.oxd-form-actions.orangehrm-login-action > button");
        this.addUser = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
        this.firstName = page.getByPlaceholder("First Name");
        this.middleName = page.getByPlaceholder("Middle Name");
        this.lasName = page.getByPlaceholder("Last Name");
        this.regiterSubmit = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
        this.pim = page.locator("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a");
        this.otherId = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input");
        this.licenseNum = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[1]/div/div[2]/input");
        this.licenseDate = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/input");
        this.checkBoxMale = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/label/input");
        this.savePersonDetails = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button");
        this.filter = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
        this.searchButton = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
        this.listUser = page.locator("#app > div.oxd-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.orangehrm-paper-container > div.orangehrm-container > div > div.oxd-table-body > div > div");
    }

    public void navigate() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login(String user,String pass) {
        userName.fill(user);
        password.fill(pass);
        loginButton.press("Enter");
        assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        System.out.println("Paso correctamente el login");
    }

    public void addUsers(String fname,String mname,String lname){
        pim.click();
        addUser.click();
        assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
        firstName.fill(fname);
        middleName.fill(mname);
        lasName.fill(lname);
        regiterSubmit.click();
        this.name=fname+" "+mname+" "+lname+" ";
        System.out.println("Paso correctamente agregar usuario");
    }

    public void addDetails(String otherID,String licensN,String licensD){
        otherId.fill(otherID);
        licenseNum.fill(licensN);
        licenseDate.fill(licensD);
        savePersonDetails.click();
    }

    public void validateEmployeeList(){
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        filter.fill(name);
        searchButton.click();
        assertThat(listUser).isVisible();
    }
}
