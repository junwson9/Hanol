import React from 'react';
import styled from 'styled-components';
// import Hammer from 'hammerjs';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import zoomPlugin from 'chartjs-plugin-zoom';
import { diagnosisResultType } from 'types/DiagnosisResult';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, zoomPlugin);

interface Props {
  title: string;
  dataList: diagnosisResultType[];
  whatValue: number;
}

const ValueGraph = ({ title, dataList, whatValue }: Props) => {
  dataList.map((data) => {
    // console.log(data.created_date.split(' ')[0].slice(2));

    switch (whatValue) {
      case 1:
        return console.log('value1:', data.value1);
      case 2:
        return console.log('value2:', data.value2);
      case 3:
        return console.log('value3:', data.value3);
      case 4:
        return console.log('value4:', data.value4);
      case 5:
        return console.log('value5:', data.value5);
      case 6:
        return console.log('value6:', data.value6);
    }
  });

  // console.log(dataList[3].created_date);

  // useEffect(() => {
  //   const myElement = document.getElementById('myElement');
  //   if (myElement) {
  //     const hammer = new Hammer(myElement as HTMLElement);

  //     // Add event listeners for specific gestures
  //     hammer.on('pan', (event) => {
  //       console.log('Pan gesture detected', event);
  //     });
  //   }
  // }, []);

  // 그래프 커스텀
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
        display: false,
        labels: { font: { size: 10, family: 'Noto Sans' } },
      },
      title: {
        display: true,
        // text: 'Chart.js Bar Chart',
      },
      zoom: {
        pan: {
          enabled: true,
          mode: 'x' as const,
          modifierKey: 'shift' as const,
          scaleMode: 'x' as const,
          threshold: 1,
        },
        // limits: {
        //   x: { min: 3, max: 15 },
        // },
        // zoom: {
        //   mode: 'xy' as const,
        //   wheel: {
        //     enabled: true,
        //     modifierKey: 'shift' as const,
        //   },
        // },
      },
    },
    elements: { point: { radius: 3 } },
    scales: { y: { display: false } },
  };
  const data = {
    // labels: [
    //   '4주 전',
    //   '3주 전',
    //   '2주 전',
    //   '1주 전',
    //   '4주 전',
    //   '3주 전',
    //   '2주 전',
    //   '1주 전',
    //   '4주 전',
    //   '3주 전',
    //   '2주 전',
    //   '1주 전',
    // ],
    // labels: ['4주 전', '3주 전', '2주 전', '1주 전'],
    labels: dataList.map((data) => data.created_date.split(' ')[0].slice(2)),
    datasets: [
      {
        label: `${title}`,
        // data: [123403, 123603, 125079, 126030, 123403, 123603, 125079, 126030, 123403, 123603, 125079, 126030],
        // data: [123403, 123603, 125079, 126030],
        // data: artistLikesCountperWeek.reverse().map((data) => data.scrapCount),
        data: dataList.map((data) => {
          switch (whatValue) {
            case 1:
              return data.value1;
            case 2:
              return data.value2;
            case 3:
              return data.value3;
            case 4:
              return data.value4;
            case 5:
              return data.value5;
            case 6:
              return data.value6;
          }
        }),
        borderColor: '#DCDFE4',
        backgroundColor: '#718093',
      },
    ],
  };

  return (
    <ValueGraphBox id="myElement">
      <Line options={options} data={data} />
    </ValueGraphBox>
  );
};

const ValueGraphBox = styled.div`
  // 추후 수정
  width: 100%;
  /* margin: 0 auto; */
  margin-top: 1.25rem;

  display: flex;
  align-items: center;
  justify-content: center;
`;
export default ValueGraph;
