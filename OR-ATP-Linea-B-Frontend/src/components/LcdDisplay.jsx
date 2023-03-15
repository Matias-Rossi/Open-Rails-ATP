import React from 'react'
import PropTypes from 'prop-types';
import styles from '../css/lcd_displays.css'


function LcdDisplay({value, status, id}) {
  value = doubleToDisplay(value);

  return (
    <div className='lcd-display' id={id}>
        <p>{status ? value : ""}</p>
    </div>
  )
}


LcdDisplay.propTypes = {
    value: PropTypes.number.isRequired,
    id: PropTypes.string.isRequired,
    status: PropTypes.bool
}
  

export default LcdDisplay

function doubleToDisplay(d) {
    const n = Math.min(d, 99.9);
    return n.toFixed(1);
}