let form = document.getElementById("form");
form.addEventListener("submit", saveUserData);

let dataFrame = JSON.parse(localStorage.getItem("userCredentials")) || [];

function saveUserData(){
    event.preventDefault();

    class Create{
        constructor(f_name, m_number, email, password){
            this.name = f_name;
            this.number = m_number;
            this.email = email;
            this.password = password;
        }
    }

    if(form.name.value==="" || form.number.value==="" || form.email.value==="" || form.password.value==="")
    {
        alert("Please Enter Complete Credentials. Sorry Account cannot be created");
    }
    else
    {
        let c1 = new Create(form.name.value, form.number.value, form.email.value, form.password.value);

        dataFrame.push(c1);
        dataFrame[0]=dataFrame[dataFrame.length-1];
        localStorage.setItem("userCredentials", JSON.stringify(dataFrame));

        form.name.value="";
        form.number.value=""; 
        form.email.value="";
        form.password.value="";

        window.location.href="login.html";
    }
}