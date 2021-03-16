//'use strict'; // ES5

//window.onload = dom;
// function dom() {

window.onload = function () {
    //console.log(document.getElementsByTagName('h1'));
    const h1s = document.getElementsByTagName('h1');
    console.log(h1s.length);
    console.log(h1s);

    for (let h1 of h1s) {
        console.log(h1);
        h1.innerText = 'Hola desde el bucle';
        h1.style = 'color: cyan';
    }

    var bSaludar = document.getElementById('btn-saludar');

    bSaludar.onclick = function () {
        const iNombre = document.getElementById('nombre');
        console.log(iNombre);

        // console.log(iNombre.value);
        alert('Hola ' + iNombre.value);

        return false; // Cabcelar la propagación del evento al navegador

    };

    const form = document.getElementById("formulario");

    /*
    form.onsubmit = function () {
        console.log('Formulario');
        alert('Formulario');
    }
    */

    form.onsubmit = function (e) {
        e.preventDefault();

        const nombreCompleto = document.getElementById('nombre-completo');
        const dni = document.getElementById('dni');

        console.log(nombreCompleto, dni);

        if (nombreCompleto.value == 'Jaime Quintana') {
            if (!nombreCompleto.classList.contains('error')) {
                //nombreCompleto.style = 'border: 1px solid red';
                nombreCompleto.classList.add('error');
                console.log('Nombre reservado');

                const errorNombre = document.createElement('span'); //<span>

                errorNombre.innerText = 'El nombre está reservado';
                errorNombre.className = 'error'; //<span class="error">El nombre está reservado</span>

                nombreCompleto.insertAdjacentElement('afterend', errorNombre);
            }
        } else {
            //nombreCompleto.style = 'border: 1px solid black';
            if (nombreCompleto.classList.contains('error')) {
                nombreCompleto.nextElementSibling.remove();
                nombreCompleto.classList.remove('error');
            }
        }

        if (!validarDni(dni.value)) {
            if (!dni.classList.contains('error')) {
                //dni.style = 'border: 2px solid red';
                dni.classList.add('error');
                console.log('El DNI es incorrecto');

                const errorSpan = document.createElement('span'); //<span>

                errorSpan.innerText = 'El DNI es incorrecto';
                errorSpan.className = 'error'; //<span class="error">El DNI es incorrecto</span>

                dni.insertAdjacentElement('afterend', errorSpan);
            }
        } else {

            if (dni.classList.contains('error')) {
                //dni.style = 'border: 1px solid black';
                dni.classList.remove('error');
                nombreCompleto.nextElementSibling.remove();
            }
        };
    };

    function validarDni(dni) {
        const letras = 'TRWAGMYFPDXBNJZSQVHLCKE';

        if (!/[XYZ\d]\d{7}[A-Z]/.test(dni)) {
            return false;
        }

        switch (dni.charAt(0)) {
            case 'X':
                dni = dni.replace('X', '0');
                break;
            case 'Y':
                dni = dni.replace('Y', '1');
                break;
            case 'Z':
                dni = dni.replace('Z', '2');
                break;
        }

        const numero = dni.substring(0, 8);

        const letra = letras[numero % 23];

        if (dni[8] !== letra) {
            //alert('No concuerda la letra del DNI');
            return false;
        }

        //alert('Todo correcto');

        return true;
    }

    //interaccionesBasicas();
    //consola();
    //tiposDatos();
    //arrays();
    //objetos();
    //navegador();
    //dom();

    function interaccionesBasicas() {
        alert('Hola Mundo desde fichero separado');

        //Declaración de variable ES5 o anterior
        var nombre = prompt('¿Como te llamas?');

        alert('Hola' + nombre);

        var confirmacion = confirm('¿Estás seguro?');

        alert(confirmacion);
    }

    // Se ve en la 'Console' del navegador
    function consola() {
        console.debug('debug');
        console.log('log');
        console.warn('warn');
        console.error('error');
    }

    function tiposDatos() {
        //Declaración de variable ES2015 o superior
        let x = 5;

        console.log(x, typeof x);

        x = 5.8

        console.log(x, typeof x);

        const texto = 'hola';

        console.log(texto, typeof texto);

        x = new Date();

        console.log(x, typeof x);

        let y;

        console.log(y, y == undefined, typeof undefined);

        let numeroRecibido, numero;

        do {
            const numeroRecibido = prompt('Dime tu nombre');

            console.log(numeroRecibido, typeof numeroRecibido);

            console.log(typeof numeroRecibido, typeof 5);

            const numero = parseInt(numeroRecibido);

            console.log(numeroRecibido, typeof numeroRecibido, numero, typeof numero);
            //} while(numero == NaN);
        } while (isNaN(numero));
        // 5 == '5' --> true
        // 5 === '5' --> false

        // Compara también si el tipo es igual (ES5)
        if (numero === 0) {
            console.log('Has acertado el número !!!!')
        } else {
            console.log('Ohhhhh, lo sentimos mucho, no ha ganado el premio')
        }
    }

    function arrays() {
        const arr = Array(3); // []

        console.log(arr);

        arr[0] = 6;
        arr[1] = 7;
        arr[2] = 8;

        arr[3] = 9;

        arr[8] = 10;

        arr[12] = 'qyeyrueyruieyr';

        arr['dato'] = new Date();

        console.log('dato', arr['dato'], 'dato', arr.dato);

        console.log(arr, arr.length);

        for (let i = 0; i < arr.length; i++) {
            console.log(arr[i]);
        }

        for (let dato of arr) {
            console.log(dato);
        }

        for (let clave in arr) {
            console.log(clave, arr[clave]);
        }

        const diccionario = [];

        diccionario['casa'] = 'Home';
        diccionario['coche'] = 'Car';

        console.log(diccionario['casa']);
        console.log(diccionario.coche);
    }

    function objetos() {
        const persona = new Object();   //{}

        console.log(persona);

        persona.id = 5;
        persona.nombreCompleto = {};
        persona.nombreCompleto.nombre = 'Jaime';
        persona.nombreCompleto['apellidos'] = 'Quintana';

        console.log(persona);

        const p = new Object();

        p.id = 10
        persona.nombre = 'Pepe';
        persona.apellidos = 'Perez';

        persona.getNombreCompleto = devolverNombreCompleto;
        persona.getId = function () {
            return this.id
        };

        console.log(persona.getId());
        console.log(persona.getNombreCompleto());

        var Persona = function (id, nombre, apellidos) {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
        }

        Persona.prototype.getNombreCompleto = devolverNombreCompleto;

        var p1 = new Persona(1, 'Primero', 'Primerez');

        console.log(p1);
        console.log(p1.getNombreCompleto());

        var p2 = { id: 1, nombre: 'Primero', apellidos: 'Primerez' };

        console.log(p2);

        console.log(p1 == p2);
    }

    function devolverNombreCompleto() {
        return this.nombre + ' ' + this.apellidos;
    }

    /*
    var devolverNombreCompleto = function () {
        return this.nombre + ' ' + this.apellidos;
    }
    */

    function navegador() {
        document.write('<h1>Hola a todos</h1>');
    }

}
