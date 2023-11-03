import Loading from 'components/Animation/Loading';

function SocketLoading() {
  return (
    <div className="relative col-span-full h-screen">
      <div className="flex justify-center content-center h-screen">
        <Loading />
      </div>
    </div>
  );
}
export default SocketLoading;
