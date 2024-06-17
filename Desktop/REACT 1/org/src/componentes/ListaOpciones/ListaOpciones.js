import "./ListaOpciones.css"

const ListaOpciones = (props) =>{

    const equipos = [
        "Programacion",
        "Frot End",
        "Data Science",
        "DevOps",
        "UX y Diseño",
        "Movil",
        "Innovacion y Gestion"
    ]

    const manejarCambio = (e) =>{
        console.log("cambio", e.target.value);
        props.actualizarequipo(e.target.value)
    }

    return <div className="lista-opciones ">
        <label > Equipos </label>
        <select value={props.valor} onChange={manejarCambio}>
            <option value={""} disabled defaultValue={""} hidden> Seleccionar equipo </option>
            {props.equipos.map((equipo,index)=> <option key={index} value={equipo}>{equipo}</option> )}

        </select>
    </div>
}

export default ListaOpciones