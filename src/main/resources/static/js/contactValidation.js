year = new Date().getFullYear();
month = new Date().getMonth() + 1;
day = new Date().getDate();
document.getElementById("birthday").max = `${year}-${month}-${day}`;

document.getElementById("contactForm").addEventListener("submit", function(e) {
    const fname = document.getElementById("fname").value.trim();
    const lname = document.getElementById("lname").value.trim();
    const email = document.getElementById("email").value.trim();
    const birthday = document.getElementById("birthday").value;
    const radioInputs = document.getElementsByName("radio_input_job");
    const cvFile =  document.getElementById("cvFile").files;
    const question = document.getElementById("question").value.trim();
    if (validate(fname, lname, email, birthday, radioInputs, cvFile, question)) {
        // do something with input data (for now just blocks it)
        e.preventDefault();
        alert("Message successfully sent!");
        document.getElementById("contactForm").reset();
    }
    else {
        e.preventDefault();
    }
})

function validate(fname, lname, email, birthday, radioInputs, cvFile, question) {
    if (fname === "") {
        alert("Please enter a valid first name!");
        return false;
    }
    if (lname === "") {
        alert("Please enter a valid last name!");
        return false;
    }
    // simple regexp for email validation
    const emailPattern = /^[a-zA-Z0-9-._]+@[a-zA-Z0-9-.]+\.[a-zA-Z]{2,}$/;
    if (email === "" || !email.match(emailPattern)) {
        alert("Please enter a valid email!");
        return false;
    }
    if (birthday === "") {
        alert("Please enter a valid birthday!");
        return false;
    }
    let selectedInput = "";
    for (let i = 0; i < radioInputs.length; i++) {
        if (radioInputs[i].checked) {
            selectedInput = radioInputs[i];
        }
    }
    if (selectedInput === "") {
        alert("Please select your job!");
        return false;
    }
    if (cvFile.length === 0) {
        alert("Please enter a valid cvFile!");
        return false;
    }
    if (question === "") {
        alert("Please enter a valid question!");
        return false;
    }
    return true;
}
