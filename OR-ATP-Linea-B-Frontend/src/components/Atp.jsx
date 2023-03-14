import '../css/atp.css'
import React from 'react'

function Atp() {
  return (
        <div id="atp">
            <div id="atp_display">
                {/* INDICACIONES */}
                <div id="atp_indicaciones">
                    <div id="CL" class="indicacion green_light gradient_light"><p>CL</p></div>
                    <div id="CMC" class="indicacion green_light gradient_light"><p>CMC</p></div>
                    <div id="TRACCION" class="indicacion yellow_light gradient_light"><p>TRACCIÓN</p></div>
                    <div id="FRENO" class="indicacion yellow_light gradient_light"><p>FRENO</p></div>
                </div>
                {/* VELOCIDAD OBJETIVO */}
                <div id="velocidad_objetivo">
                    <div id="PENALIZACION" class="indicacion red_light gradient_light"><p>PENALIZACIÓN</p></div>
                    <div id="vZiel_display" class="lcd">8<span>0.0</span></div>
                </div>
                {/* VELOCIDAD PERMITIDA */}
                <div id="velocidad_permitida_bar">
                    <div id="vSoll_display" class="speed_bar"><div class="speed_bar_cursor"></div></div> {/* TODO: Agregar ID */}
                </div>
                {/* VELOCIDAD (display) */}
                <div id="velocidad_actual">
                    <div id="vIst_display" class="lcd">8<span>0.0</span></div>
                </div>
                {/* VELOCIDAD (bar) */}
                <div id="velocidad_actual_bar">
                    <div id="vIst_bar" class="speed_bar"><div class="speed_bar_cursor"></div></div>  {/* TODO: Agregar ID */}
                </div>
            </div>
            <div id="atp_lower"></div>
    </div>
  )
}

export default Atp