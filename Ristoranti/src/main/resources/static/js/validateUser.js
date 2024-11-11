function validate(event) {
    event.preventDefault();
    let username = document.getElementById("user").value;
    if (username.includes("@")) {
        let form = document.getElementById('loginForm');
        form.submit();
    }
    else {
        alert("NO!! Missing \'@\'")
    }
}

// nel caso in cui non si volesse usare onsubmit sul file HTML
window.addEventListener("load", function () {
    let form = document.getElementById('loginForm');
    form.addEventListener('submit', validate);
});
