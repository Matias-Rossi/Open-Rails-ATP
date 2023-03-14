import React from 'react'
import PropTypes from 'prop-types';
import styles from '../css/speedbar.css'



function SpeedBar({value, id, status}) {
  value = Math.min(value, 100)
  const fillerWidthStyle = status == 'on'? {width: `${value}%`} : {width: `0%`};
  

  return (
    <div className='sb-container' id={id}>
        <div className='sb-filler' style={fillerWidthStyle}>
            
        </div>
    </div>
  )
}


SpeedBar.propTypes = {
    value: PropTypes.number.isRequired,
    status: PropTypes.string.isRequired,
    id: PropTypes.string.isRequired,
}

export default SpeedBar
