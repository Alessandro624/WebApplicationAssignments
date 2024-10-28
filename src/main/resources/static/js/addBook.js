choice = true;
document.getElementById("year").max = new Date().getFullYear();

document.getElementById("addBookButton").addEventListener("click", function() {
    document.getElementById("bookForm").style.display = "block";
    document.getElementById("removeBookForm").style.display = "none";
});

document.getElementById("addBookForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const title = document.getElementById("title").value.trim();
    const publisher = document.getElementById("publisher").value.trim();
    const year = document.getElementById("year").value.trim();
    if (validate(title, publisher, year)) {
        const tbody = document.getElementById("booksTable").getElementsByTagName("tbody")[0];
        choice ? addNewBook(tbody, title, publisher, year) : addNewBook2(tbody, title, publisher, year);
        document.getElementById("addBookForm").reset();
        choice = !choice;
    }
})

function validate(title, publisher, year) {
    if (title === "") {
        alert("Please enter a valid title!");
        return false;
    }
    else if (publisher === "") {
        alert("Please enter a valid publisher!");
        return false;
    }
    else if (year === "") {
        alert("Please enter a valid year!");
        return false;
    }
    return true;
}

function addNewBook(tbody, title, publisher, year) {
    console.log("Inserting new book with method 1");
    const newBook = `
        <tr>
            <td>${title}</td>
            <td>${publisher}</td>
            <td><span class="badge bg-primary d-block mx-auto">${year}</span></td>
            <td><button class="btn btn-danger btn-sm d-block mx-auto deleteButton" onclick="deleteBook(event)"><i class="fa-solid fa-trash"></i></button></td>
        </tr>
    `;

    tbody.innerHTML += newBook;
}

function addNewBook2(tbody, title, publisher, year) {
    console.log("Inserting new book with method 2");
    const newRow = tbody.insertRow();
    const titleCell = newRow.insertCell(0);
    const publisherCell = newRow.insertCell(1);
    const yearCell = newRow.insertCell(2);
    const buttonCell = newRow.insertCell(3);
    titleCell.textContent = title;
    publisherCell.textContent = publisher;
    yearCell.innerHTML = `<span class="badge bg-primary d-block mx-auto">${year}</span></td>`;
    buttonCell.innerHTML = `<button class="btn btn-danger btn-sm d-block mx-auto deleteButton" onclick="deleteBook(event)"><i class="fa-solid fa-trash"></i></button>`;
}
