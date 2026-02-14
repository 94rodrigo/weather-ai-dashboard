import { useTranslation } from 'react-i18next';

type Props = {
    onAdd: () => void;
    disabled: boolean;
    width: number;
};

export function AddColumnButton({ onAdd, disabled, width }: Props) {
    const { t } = useTranslation();

    return (
        <div 
            className="background-color-2 h-dvh border-2 border-dashed border-gray-400 rounded-lg flex items-center justify-center flex-shrink-0"
            style={{ width: `${width}%` }}
        >
            <button
                onClick={onAdd}
                disabled={disabled}
                className="px-4 py-2 bg-blue-600 text-white rounded disabled:bg-gray-400"
            >
                {t('addColumn')}
            </button>
        </div>
    );
}