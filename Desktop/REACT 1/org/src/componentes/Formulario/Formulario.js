import { useState} from "react"
import "./Formulario.css"
import Campo from "../Campo"
import ListaOpciones from "../ListaOpciones/ListaOpciones"
import Boton from "../Boton"

const Formulario = (props) => {

    const [nombre , actualizacionNombre] = useState("")
    const [puesto , actualizacionPuesto] = useState("")
    const [foto , actualizacionFoto] = useState("")
    const [equipo , actualizarequipo] = useState("")

    const [titulo , actualizarTitulo] = useState("")
    const [color , actualizarColor] = useState("")

    const{ registarColaborador ,crearEquipo } = props

    const manejarEnvio = (evento) =>{
        evento.preventDefault()
        console.log("Manejar El envio" );
        let datosAenviar = {
            nombre: nombre,
            puesto: puesto,
            foto: foto,
            equipo: equipo
        }

        registarColaborador(datosAenviar);
    }

    const manejarNuevoEquipo = (evento) => {
        evento.preventDefault()
        crearEquipo({ titulo, colorPrimario: color });
    }

    return( 
    <section className="formulario"> 

        <form onSubmit={manejarEnvio}>
            <h2> Rellena el formulario para crear el colaborador. </h2>
            <Campo
                titulo="Nombre" 
                placeholder="Ingresar nombre" 
                required 
                valor ={nombre} 
                actualizarValor={actualizacionNombre}
            />

            <Campo
                titulo="Puesto" 
                placeholder="Ingresar puesto" 
                required
                valor = {puesto}
                actualizarValor = {actualizacionPuesto}
            />
            
            <Campo 
                titulo="Foto"
                placeholder="Ingresar enlace de foto" 
                required
                valor = {foto}
                actualizarValor = {actualizacionFoto}
            />
            <ListaOpciones
                valor = {equipo}
                actualizarequipo = {actualizarequipo}
                equipos={props.equipos}
            
            />
            <Boton texto="Crear"/>
        </form>

        <form onSubmit={manejarNuevoEquipo}>
            <h2> Rellena el formulario para crear el equipo. </h2>
            <Campo
                titulo="Titulo" 
                placeholder="Ingresar titulo" 
                required 
                valor ={titulo} 
                actualizarValor={actualizarTitulo}
            />

            <Campo 
                titulo="Color" 
                placeholder="Ingresar el color en Hex" 
                required
                valor = {color}
                actualizarValor = {actualizarColor}
                type = "color"
            />

            <Boton texto="Registrar Equipo"/>
        </form>
    </section>)
}

export default Formulario