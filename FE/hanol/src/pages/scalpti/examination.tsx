import TapBar from 'components/common/TopBar';
// import TopTab from 'components/common/TopTabNew';
function Examination() {
  return (
    <div className="col-span-full">
      <div>
        <TapBar name={'간편문진'} />
      </div>
      <div>{/* <TopTab Indicator="one" title1="대시보드" title2="진단 결과" /> */}</div>
    </div>
  );
}
export default Examination;
