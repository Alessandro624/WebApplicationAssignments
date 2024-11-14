window.addEventListener('load', () => {
    btn_aggiungi.addEventListener('click', aggiungiRistorante);
})

function aggiungiRistorante() {
    const txtNome = document.querySelector('#nome_ristorante');
    const txtDescrizione = document.querySelector('#desc_ristorante');
    const txtUbicazione = document.querySelector('#ub_ristorante');
    const nome = txtNome.value;
    const descrizione = txtDescrizione.value;
    const ubicazione = txtUbicazione.value;
    const ristorante = {
        "nome": nome,
        "descrizione": descrizione,
        "ubicazione": ubicazione
    };
    $.ajax({
        url: "/addRistorante",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(ristorante),
        success: function (res) {
            console.log(res);
            const tbody = document.getElementById('tabellaRistorante');
            const newRow = tbody.insertRow();
            const newName = newRow.insertCell(0);
            const newDescrizione = newRow.insertCell(1);
            const newUbicazione = newRow.insertCell(2);
            newName.innerHTML = nome;
            newDescrizione.innerHTML = descrizione;
            newUbicazione.innerHTML = ubicazione;
        }
    });
    alert(ristorante.nome + " " + ristorante.descrizione + " " + ristorante.ubicazione);
}
