import React, { useMemo } from 'react';
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
  title?: string;
  dataList: diagnosisResultType[];
  graphValue: number;
  setIndex: (arg: number) => void;
}

const ValueGraph = ({ title, dataList, graphValue, setIndex }: Props) => {
  const reversedDataList = useMemo(() => dataList.slice().reverse(), [dataList]);

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
    // eslint-disable-next-line
    onClick: function (point: any, event: any) {
      if (event[0]) {
        // console.log('event : ', event[0].index);
        // console.log('point : ', point);
        setIndex(dataList.length - 1 - event[0].index);
      }
    },
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
    elements: { point: { radius: 5 } },
    scales: { y: { display: false, suggestedMin: 0, suggestedMax: 3, ticks: { stepSize: 1 } } },
  };
  const data = {
    labels: reversedDataList?.map((data) => data.created_date.split(' ')[0].slice(2)),
    datasets: [
      {
        label: `${title}`,
        data: reversedDataList?.map((data) => {
          switch (graphValue) {
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
      <Line options={options} data={data} className="graph" />
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

  .graph {
    overflow-x: scroll;
  }
`;
export default ValueGraph;
