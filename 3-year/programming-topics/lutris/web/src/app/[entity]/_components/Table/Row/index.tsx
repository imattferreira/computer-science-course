import type { Entities } from "~/constants/entities";
import Actions from "./Actions";
import Item from "./Item";

interface RowProps {
  data: (string | number)[];
  domain: Entities;
  domainId: number;
}

function Row({ domainId, data, domain }: RowProps) {
  return (
    <tr className="border-t border-t-zinc-700">
      {data.map((d) => (
        <Item key={d}>{d}</Item>
      ))}
      <Item>
        <Actions domainId={domainId} domain={domain} />
      </Item>
    </tr>
  );
}

export default Row;
