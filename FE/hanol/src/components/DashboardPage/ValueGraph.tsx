import React, { useEffect } from 'react';
import styled from 'styled-components';
import Hammer from 'hammerjs';
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

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, zoomPlugin);

interface Props {
  title: string;
}

const ValueGraph = ({ title }: Props) => {
  useEffect(() => {
    const myElement = document.getElementById('myElement');
    if (myElement) {
      const hammer = new Hammer(myElement as HTMLElement);

      // Add event listeners for specific gestures
      hammer.on('pan', (event) => {
        console.log('Pan gesture detected', event);
      });
    }
  }, []);

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
    labels: [
      '4주 전',
      '3주 전',
      '2주 전',
      '1주 전',
      '4주 전',
      '3주 전',
      '2주 전',
      '1주 전',
      '4주 전',
      '3주 전',
      '2주 전',
      '1주 전',
    ],
    datasets: [
      {
        label: `${title}`,
        data: [123403, 123603, 125079, 126030, 123403, 123603, 125079, 126030, 123403, 123603, 125079, 126030],
        // data: artistLikesCountperWeek.reverse().map((data) => data.scrapCount),
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
  width: 19.625em;
  height: 16.75rem;
  margin: 0 auto;
`;
export default ValueGraph;
