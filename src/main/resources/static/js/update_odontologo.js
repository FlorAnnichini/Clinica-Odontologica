window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_domicilio_form');

    formulario.addEventListener('submit', function (event) {
        let domicilioId = document.querySelector('#id_domicilio').value;

        const formData = {
            id: document.querySelector('#id_domicilio').value,
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value,

        };

        const url = '/domicilio/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    function findBy(id) {
          const url = '/domicilio'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let domicilio = data;
              document.querySelector('#id_domicilio').value = domicilio.id;
              document.querySelector('#calle').value = domicilio.calle;
              document.querySelector('#numero').value = domicilio.numero;
              document.querySelector('#localidad').value = domicilio.localidad;
              document.querySelector('#provincia').value = domicilio.provincia;

              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }