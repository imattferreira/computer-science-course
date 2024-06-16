// TODO: create loading states

import { BarChart2 } from "lucide-react";
import Title from "./_components/Title";
import api from "~/api";
import CounterBox from "./_components/CounterBox";
import { StatisticsCounter } from "~/api/statistics";
import { ENTITIES_TYPES, type Entities } from "~/constants/entities";

export const dynamic = "force-dynamic";

const counterToBox = (counter: StatisticsCounter) => {
  const entries = Object.entries(counter);
  const result: [string, number][] = [];

  for (const [key, value] of entries) {
    result.push([ENTITIES_TYPES[key as Entities], value]);
  }

  return result;
};

async function Home() {
  const counter = await api.statistics.count();

  return (
    <>
      <Title icon={BarChart2}>Estatísticas</Title>
      <div className="grid grid-cols-2 gap-4 max-w-screen-lg mx-auto">
        {counterToBox(counter).map(([title, counter]) => (
          <CounterBox title={title} counter={counter} key={title} />
        ))}
      </div>
    </>
  );
}

export default Home;
