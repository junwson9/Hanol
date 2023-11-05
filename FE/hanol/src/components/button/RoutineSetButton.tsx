// RoutineSetButton.tsx
import { ReactComponent as UnActiveCheck } from 'assets/icons/check-unactive.svg';
import { ReactComponent as Check } from 'assets/icons/check.svg';
// import { useState } from 'react';

type RoutineSetButtonProps = {
  routineName: string;
  active: boolean; // active prop
  onClick: () => void;
};

function RoutineSetButton({ routineName, active, onClick }: RoutineSetButtonProps) {
  return (
    <div>
      <div
        className={`flex h-14 mt-3 border ${
          active ? 'border-Main text-Main font-medium' : 'text-GrayForText font-medium'
        } rounded-lg items-center gap-2.5 whitespace-nowrap cursor-pointer`}
        onClick={onClick}
      >
        {active ? <Check className="ml-2.5" /> : <UnActiveCheck className="ml-2.5" />}
        {routineName}
      </div>
    </div>
  );
}

export default RoutineSetButton;
