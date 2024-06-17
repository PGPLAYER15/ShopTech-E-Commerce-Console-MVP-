import { useState } from "react"
import "./MiOrg.css"


const MiOrg = (props)=>{

    /* const [mostrar , actualizacionMostar] = useState(true)
    console.log(props)
    const manejarClick = ()=>{
        console.log("mostrar/ocultar Elemento :D")
        actualizacionMostar(!mostrar)
    } */

    return (
        <section className="orgSection">
            <h3 className="title"> Mi organizacion </h3>
            <img src="/img/add.png" alt="add" onClick={props.cambiarmostar}/>
        </section>
    )
}

export default MiOrg