import React from 'react';
import FloatingButton from 'components/button/FloatingButton';
import Button from 'components/button/Button';
import EmptyButton from 'components/button/EmptyButton';

function Login() {
  const login = 'login';
  return (
    <div className="col-span-full">
      <p className="font-medium">{login}</p>
      <div>
        <FloatingButton />
      </div>
      <div>
        <Button />
      </div>
      <div>
        <EmptyButton />
      </div>
    </div>
  );
}

export default Login;
