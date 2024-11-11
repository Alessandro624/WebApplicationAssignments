document.getElementById("removeBookButton").addEventListener("click", function () {
    document.getElementById("bookForm").style.display = "none";
    document.getElementById("removeBookForm").style.display = "block";
});

document.querySelectorAll(".deleteButton").forEach(button => {
    button.addEventListener("click", function () {
        console.log("Deleting book with method 1");
        const row = button.closest("tr");
        row.parentNode.removeChild(row);
    })
});

function removeBookByNumber(event) {
    console.log("Deleting book by number");
    event.preventDefault();
    const bookNumber = parseInt(document.getElementById("bookNumber").value.trim());
    const rows = document.getElementById("booksTable").querySelectorAll("tbody tr");
    if (bookNumber > 0 && bookNumber <= rows.length) {
        rows[bookNumber - 1].remove();
        document.getElementById("removeBookByNumberForm").reset();
        alert("Book successfully removed!");
    }
    else {
        alert("Book number not valid");
    }
}

function deleteBook(event) {
    console.log("Deleting book with method 2");
    const row = event.target.closest("tr");
    row.parentNode.removeChild(row);
}
