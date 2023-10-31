import React from 'react';

type ButtonProps = {
  name: string;
  onClick: () => void;
};

function EmptyButton({ name, onClick }: ButtonProps) {
  return (
    <button
      className="w-full h-[54px] rounded-xl justify-center items-center gap-2.5 inline-flex bg-White border border-Main"
      onClick={() => onClick()}
    >
      <div className="text-Main text-base font-medium text-[16px] tracking-wide">{name}</div>
    </button>
  );
}

export default EmptyButton;
