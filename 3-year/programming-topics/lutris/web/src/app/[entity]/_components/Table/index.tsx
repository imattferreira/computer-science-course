import { twJoin } from "tailwind-merge";
import Header from "./Header";
import Row from "./Row";
import type { Entities } from "~/constants/entities";

interface TableProps {
  columns: string[];
  className: string;
  domain: Entities;
  rows: (string | number)[][];
}

function Table({ columns, className, domain, rows }: TableProps) {
  return (
    <table
      className={twJoin(
        "w-full max-w-screen-xl mx-auto table-fixed rounded-md bg-zinc-950 overflow-hidden",
        className
      )}
    >
      <Header columns={columns} />
      <tbody className="">
        {rows.map((row) => (
          <Row
            domainId={row[0] as number}
            domain={domain}
            data={row}
            key={row[0]}
          />
        ))}
      </tbody>
    </table>
  );
}

export default Table;
