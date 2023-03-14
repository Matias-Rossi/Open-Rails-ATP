import React from 'react'
import PropTypes from 'prop-types';
import styles from '../css/indicator_light.css'


function IndicatorLight({styles = "", text = "", status = false, color = "", id=""}) {

  const colorClass = `il-${color}-${status? "on" : "off"}`

  return (
    <div className={'ind-light ' + colorClass} id={id} >
        {text}
    </div>
  )
}

IndicatorLight.propTypes = {
  color: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
  status: PropTypes.string
}

export default IndicatorLight
