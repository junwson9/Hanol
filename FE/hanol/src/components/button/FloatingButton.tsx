import React from 'react';

function FloatingButton() {
  const name = 'name';
  return (
    <div className="w-full h-[54px] rounded-xl drop-shadow-xl justify-center items-center gap-2.5 inline-flex bg-Main">
      <div className="text-White text-base font-medium text-[16px] tracking-wide">{name}</div>
    </div>
  );
}

export default FloatingButton;
