import React from 'react'
import base from '../css/base.css'
import IndicatorLight from './IndicatorLight';
import LcdDisplay from './LcdDisplay.jsx';
import SpeedBar from './SpeedBar';

function Atp() {
  //TODO: Handle flashing lights
  const vCurr = 44.6;
  const vObj = 60.0;
  const vAll = 60.0;

  const cl_status = false;
  const cm_status = true;
  const tr_status = true;
  const fr_status = false;
  const pe_status = false;
  
  return (
    <div className='base'>
      {/* Indicator Lights */}
        <IndicatorLight text="CL"           status={cl_status} color='green' id="CL_LI" />
        <IndicatorLight text="CMC"          status={cm_status} color='green' id="CMC_LI" />
        <IndicatorLight text="TRACCIÓN"     status={tr_status} color='yellow' id="TR_LI" />
        <IndicatorLight text="FRENO"        status={fr_status} color='yellow' id="FR_LI" />
        <IndicatorLight text="PENALIZACIÓN" status={pe_status} color='red' id="PEN_LI"/>

      {/* LCD Speed Displays */}
      <LcdDisplay value={vCurr} status='on' id='vCurr-display'/>
      <LcdDisplay value={vObj} status='on' id='vObj-display'/>

      {/* LCD Speed Bars */}
      <SpeedBar value={vCurr} status='on' id='vCurr-SB'/>
      <SpeedBar value={vAll} status='on' id='vAll-SB'/>

    </div>    
    
  )
}

export default Atp