import React from 'react';
type FloatButtonProps = {
  name: string;
  onClick: () => void;
};

function FloatingButton({ name, onClick }: FloatButtonProps) {
  return (
    <button
      onClick={onClick}
      className="w-full h-[54px] rounded-xl drop-shadow-xl justify-center items-center gap-2.5 inline-flex bg-Main"
    >
      <div className="text-White text-base font-medium text-[16px] tracking-wide">{name}</div>
    </button>
  );
}

export default FloatingButton;
