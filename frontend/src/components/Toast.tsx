import React, { useEffect } from 'react';
import type { ToastMessage } from '../interfaces/ToastTypes';
import { motion } from "motion/react"

interface ToastProps {
  toast: ToastMessage;
  onClose: (id: string) => void;
}

const toastStyles: Record<string, string> = {
  success: 'bg-green-50 border-green-500 text-green-800',
  error: 'bg-red-50 border-red-500 text-red-800',
  info: 'bg-blue-50 border-blue-500 text-blue-800',
  warning: 'bg-yellow-50 border-yellow-500 text-yellow-800',
};

export const Toast: React.FC<ToastProps> = ({ toast, onClose }) => {
  useEffect(() => {
    const timer = setTimeout(() => onClose(toast.id), 5000);
    return () => clearTimeout(timer);
  }, [toast.id, onClose]);

  return (
    <motion.div
        className={`flex items-center p-4 mb-4 border-l-4 rounded shadow-md translate-x-0 ${toastStyles[toast.type]}`}
        role="alert"
        initial={{ opacity: 0, scale: 0.5 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{
            duration: 0.8,
            delay: 0.5,
            ease: [0, 0.71, 0.2, 1.01],
        }}
    >
      <div className="flex-1 text-sm font-medium">{toast.message}</div>
      <button
        onClick={() => onClose(toast.id)}
        className="ml-4 p-1 rounded hover:bg-black hover:bg-opacity-10 cursor-pointer focus:outline-none transition-all"
        aria-label="Close notification"
        type="button"
      >
        <svg className="w-5 h-5" stroke="currentColor" fill="none" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2.5" d="M6 18L18 6M6 6l12 12" />
        </svg>
      </button>
    </motion.div>
  );
};