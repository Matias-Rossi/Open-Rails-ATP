import { useState, useEffect } from 'react'
import base from './css/base.css'
import st from './css/style.css'
import Atp from './components/atp'

function App() {

  const [blinker, setBlinker] = useState(false)

  useEffect(() => {
    const interval = setInterval(()=> {
      setBlinker(prevBlinker => !prevBlinker);
    }, 500)
    return () => {
      clearInterval(interval)
    }
  }, []);


  return (
    <div>
      <Atp blinker={blinker}/>
    </div>
  )
}

export default App
