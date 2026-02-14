import LanguageDropdown from './LanguageDropdown'
import logo from '../assets/pixel_logo.png'


export default function Header() {
  return (
    <header className="w-full bg-gray-900 main-header-1">
      <nav aria-label="Global" className="w-full flex items-center justify-between h-16 px-6 lg:px-8">
        <div className="flex lg:flex-1 h-full">
          <a href="#" className="block h-full items-center">
            <span className="sr-only">Your Company</span>
            <img
              alt=""
              src={logo}
              className="h-full w-auto object-contain"
            />
          </a>
        </div>
        <div className="hidden lg:flex lg:flex-1 lg:justify-end">
          <LanguageDropdown />
        </div>
      </nav>
    </header>
  )
}
