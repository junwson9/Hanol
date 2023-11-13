import React, { createContext, useContext, useState, ReactNode } from 'react';

interface ErrorContextProps {
  error: Error | null;
  setError: React.Dispatch<React.SetStateAction<Error | null>>;
}

const ErrorContext = createContext<ErrorContextProps | undefined>(undefined);

interface ErrorProviderProps {
  children: ReactNode;
}

export const ErrorProvider: React.FC<ErrorProviderProps> = ({ children }) => {
  const [error, setError] = useState<Error | null>(null);

  return <ErrorContext.Provider value={{ error, setError }}>{children}</ErrorContext.Provider>;
};

export const useError = () => {
  const context = useContext(ErrorContext);
  if (!context) {
    throw new Error('useError must be used within an ErrorProvider');
  }
  return context;
};
