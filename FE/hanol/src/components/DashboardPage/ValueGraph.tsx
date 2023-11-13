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

  //날짜 포맷 변경
  const extractMonthAndDay = (dateString: string) => {
    const dateComponents = dateString.split(' ')[0].split('-');
    const month = dateComponents[1];
    const day = dateComponents[2];
    return `${month}-${day}`;
  };

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
    },
    elements: { point: { radius: 7, hitRadius: 3 }, line: { borderWidth: 5 } },
    scales: {
      y: {
        display: true,
        suggestedMin: -1,
        suggestedMax: 4,
        ticks: {
          stepSize: 1,
          // eslint-disable-next-line
          callback: function (label: any) {
            switch (label) {
              case 0:
                return '우수';
              case 1:
                return '양호';
              case 2:
                return '주의';
              case 3:
                return '위험';
              default:
                return '';
            }
          },
          color: [
            '',
            'rgba(91, 195, 196, 1)',
            'rgba(107, 228, 100, 1)',
            'rgba(251, 222, 72, 1)',
            'rgba(234, 83, 111, 1)',
            '',
          ],
        },
        grid: {
          color: [
            '',
            'rgba(91, 195, 196, 0.2)',
            'rgba(107, 228, 100, .2)',
            'rgba(251, 222, 72, 0.2)',
            'rgba(234, 83, 111, .2)',
            '',
          ],
          lineWidth: 3,
        },
      },
      x: {
        grid: {
          display: false,
        },
      },
    },
  };
  const data = {
    labels: reversedDataList?.map((data) => extractMonthAndDay(data.created_date)),
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
