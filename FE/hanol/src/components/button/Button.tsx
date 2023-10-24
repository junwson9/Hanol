import React from 'react';

function Button() {
  const name = 'name';
  return (
    <div className="w-full h-[54px] rounded-xl justify-center items-center gap-2.5 inline-flex bg-Main">
      <div className="text-white text-base font-medium text-[16px] tracking-wide">{name}</div>
    </div>
  );
}

export default Button;
