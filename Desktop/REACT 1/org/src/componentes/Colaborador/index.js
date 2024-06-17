import "./Colaborador.css"
import { IoIosCloseCircle,IoMdHeart,IoIosHeartEmpty  } from "react-icons/io";

const Colaborador = (props) =>{
    const {nombre , puesto , foto, equipo , id ,fav} = props.datos
    const{colorPrimario,eliminarColaborador,like } = props




    return <div className="colaborador" >
        <IoIosCloseCircle className="eliminar" onClick={() => eliminarColaborador(id)}/>
        <div className="encabezado" style={{backgroundColor: colorPrimario}}>        
            <img src= {foto} alt={nombre}/> 
        </div>
        <div className="info">
            <h4> {nombre} </h4>
            <h4> {puesto}</h4>
            {fav ?  <IoMdHeart color="red" onClick={()=> like(id)}/> : <IoIosHeartEmpty onClick={()=> like(id)}/>}
            
        </div>
    </div>
}

export default Colaborador