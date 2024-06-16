import type { Entities } from "~/constants/entities";
import Delete from "./Delete";
import Edit from "./Edit";

interface ActionsProps {
  domain: Entities;
  domainId: number;
}

function Actions({ domain, domainId }: ActionsProps) {
  return (
    <div className="flex items-center justify-center gap-x-2">
      <Edit domainId={domainId} domain={domain} />
      <Delete domainId={domainId} domain={domain} />
    </div>
  );
}

export default Actions;
