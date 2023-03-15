import React, { useState, useEffect } from 'react'
import base from '../css/base.css'
import IndicatorLight from './IndicatorLight';
import LcdDisplay from './LcdDisplay.jsx';
import SpeedBar from './SpeedBar';

function Atp({blinker}) {
  //TODO: Handle flashing lights
  const [cl_status, setCl_status] = useState(false);
  const [cm_status, setCm_status] = useState(true);
  const [tr_status, setTr_status] = useState(true);
  const [fr_status, setFr_status] = useState(false);
  const [pe_status, setPe_status] = useState(false);

  const [vCur, setVCur] = useState(0.0);
  const [vObj, setVObj] = useState(0.0);
  const [vAll, setVAll] = useState(0.0);

  useEffect(() => {
    const interval = setInterval(()=> {
      //fetch panel
      fetch("http://localhost:8080/atp")
      .then((response) => response.json())
      .then((data) => {
      setCl_status(onOffToBool(data[0]["status"]));
      setCm_status(onOffToBool(data[1]["status"]));
      setTr_status(onOffToBool(data[2]["status"]));
      setFr_status(onOffToBool(data[3]["status"]));
      setPe_status(onOffToBool(data[4]["status"]));
      setVCur(data[5]["speed"]);
      setVObj(data[6]["speed"]);
      setVAll(data[7]["speed"]);
    });
    }, 500)
    return () => {
      clearInterval(interval)
    }
  }, [])
  

  
  return (
    <div className='base'>
      {/* Indicator Lights */}
        <IndicatorLight text="CL"           status={cl_status} color='green' id="CL_LI" />
        <IndicatorLight text="CMC"          status={cm_status} color='green' id="CMC_LI" />
        <IndicatorLight text="TRACCIÓN"     status={tr_status} color='yellow' id="TR_LI" />
        <IndicatorLight text="FRENO"        status={fr_status} color='yellow' id="FR_LI" />
        <IndicatorLight text="PENALIZACIÓN" status={pe_status} color='red' id="PEN_LI"/>

      {/* LCD Speed Displays */}
      <LcdDisplay value={vCur} status={true} id='vCurr-display'/>
      <LcdDisplay value={vObj} status={vObj < vCur? blinker : true} id='vObj-display'/>

      {/* LCD Speed Bars */}
      <SpeedBar value={vCur} status='on' id='vCurr-SB'/>
      <SpeedBar value={vAll} status='on' id='vAll-SB'/>

    </div>    
    
  )
}

function onOffToBool(str) {
  return str === "ON" ? true : false;
}




export default Atp