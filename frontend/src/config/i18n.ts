import i18n from 'i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import Backend from 'i18next-http-backend';
import { initReactI18next } from 'react-i18next';
import i18n_de from "./labels/labels_de.json";
import i18n_en from "./labels/labels_en.json";
import i18n_es from "./labels/labels_es.json";
import i18n_fr from "./labels/labels_fr.json";
import i18n_pt from "./labels/labels_pt.json";

i18n
    .use(Backend)
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        fallbackLng: 'en',
        debug: false,
        ns: ['translation'],
        defaultNS: 'translation',
        interpolation: {
            escapeValue: false, // not needed for react as it escapes by default
        },
        react: {
            useSuspense: false,
        },
        resources: {
            en: i18n_en,
            pt: i18n_pt,
            fr: i18n_fr,
            es: i18n_es,
            de: i18n_de,
        }
    });

export default i18n;