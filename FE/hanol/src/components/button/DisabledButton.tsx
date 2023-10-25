import React from 'react';

type ButtonProps = {
  name: string;
  onClick: () => void;
  disabled: boolean;
};

function DisabledButton({ name, onClick, disabled }: ButtonProps) {
  const buttonClasses = `w-full h-[54px] rounded-xl justify-center items-center gap-2.5 inline-flex bg-Main ${
    disabled ? 'bg-opacity-50' : ''
  }`;

  return (
    <div className={buttonClasses} onClick={!disabled ? onClick : undefined}>
      <div className="text-white text-base font-medium text-[16px] tracking-wide">{name}</div>
    </div>
  );
}

export default DisabledButton;
