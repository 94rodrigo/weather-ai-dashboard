import './App.css'
import { ToastProvider } from './components/ToastContext'
import Home from './pages/Home'

function App() {
  return (
    <div className="min-h-screen background-color-1">
      <div className="app-content">
        <ToastProvider>
          <Home />
        </ToastProvider>
      </div>
    </div>
  )
}

export default App
