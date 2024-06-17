import "./Equipo.css"
import Colaborador from "../Colaborador"
import hexToRgba from 'hex-to-rgba';
const Equipo = (props) =>{

    const{ colorPrimario , colorSecundario ,titulo, id } = props.datos
    const{ colaboradores , eliminarColaborador, actualizarColor , like } = props

    const obj = {
        backgroundColor: hexToRgba(colorPrimario , 0.6)
    }

    

    const estiloTItulo = { borderColor: colorPrimario}

    return <>
    { 
        colaboradores.length > 0 &&
        (<section className="Equipo" style={{backgroundColor:obj.backgroundColor }}>
            <input
                type="color"
                className="input-color"
                value={colorPrimario}
                onChange={(evento)=>{
                    actualizarColor(evento.target.value ,id);
                }}
            
            />
            <h3 style={estiloTItulo}> {titulo}</h3>
            <div className="colaboradores">
                
                {
                    colaboradores.map( (colaborador,index)=> <Colaborador 
                    datos = {colaborador} 
                    key = {index} 
                    colorPrimario={colorPrimario} 
                    eliminarColaborador = {eliminarColaborador}
                    like = {like}
                    />)
                }    
            </div>
        </section>
    )}
    </>
}

export default Equipo