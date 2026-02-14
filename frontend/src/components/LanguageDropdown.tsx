import { Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/react";
import { ChevronDownIcon } from "@heroicons/react/20/solid";
import { useState, type JSX } from "react";
import { useTranslation } from 'react-i18next';
import i18n from "../config/i18n";
import WarningDialog from "./Dialog";

export default function LanguageDropdown() {
    const languages = [
        { code: 'de', name: 'Deutch' },
        { code: 'en', name: 'English' },
        { code: 'es', name: 'Espanõl' },
        { code: 'fr', name: 'Français' },
        { code: 'pt', name: 'Português' },
    ];

    const[selectedLanguage, setSelectedLanguage] = useState(detectInitialLanguage(i18n.language));
    const { t } = useTranslation();
    const [openModal, setOpenModal] = useState(false);

    function detectInitialLanguage(initialLanguage: string): string {
      let firstLanguage = languages.find(lang => initialLanguage.startsWith(lang.code))?.code;
      return firstLanguage ? firstLanguage : 'en';
    }

    function getLanguagesMenuItens(): JSX.Element[] {
      return languages.map((language) => {
        return (
            <MenuItem key={language.code}>
              <a
                href="#"
                className="block px-4 py-2 text-sm text-gray-300 data-focus:bg-white/5 data-focus:text-white data-focus:outline-hidden"
                onClick={() => {
                  setSelectedLanguage(language.code);
                  i18n.changeLanguage(language.code);
                  setOpenModal(true);
                }}
              >
                {language.name}
              </a>
            </MenuItem>
        );
      });
    }

  return (
    <Menu as="div" className="relative inline-block">
      <MenuButton className="inline-flex w-full justify-center gap-x-1.5 rounded-md bg-white/10 px-3 py-2 text-sm font-semibold text-white inset-ring-1 inset-ring-white/5 hover:bg-white/20">
        {selectedLanguage.toUpperCase()}
        <ChevronDownIcon
          aria-hidden="true"
          className="-mr-1 size-5 text-gray-400"
        />
      </MenuButton>

      <MenuItems
        transition
        className="absolute right-0 z-10 mt-2 w-56 origin-top-right rounded-md bg-gray-800 outline-1 -outline-offset-1 outline-white/10 transition data-closed:scale-95 data-closed:transform data-closed:opacity-0 data-enter:duration-100 data-enter:ease-out data-leave:duration-75 data-leave:ease-in"
      >
        <div className="py-1">
            {getLanguagesMenuItens()}
        </div>
      </MenuItems>
      {openModal && (
        <WarningDialog
            dialogDescription={t('confirmChangeLanguageDescription')}
            dialogTitle={t('confirmChangeLanguageTitle')}
            primaryButtonText={t('yes')}
            secondaryButtonText={t('no')}
            open={openModal}
            setOpen={setOpenModal}
            onPrimaryButtonClick={() => {
              setOpenModal(false);
              // notify other components to refresh their data before reloading
              window.dispatchEvent(new CustomEvent('refreshAllWeather'));
            }}
            onSecondaryButtonClick={() => {
              setOpenModal(false);
            }}
        />
      )}
    </Menu>
  );
}
