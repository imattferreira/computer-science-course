import employees from "./employees";
import dependents from "./dependents";
import sectors from "./sectors";
import units from "./units";
import statistics from "./statistics";

const api = {
  employees,
  dependents,
  sectors,
  units,
  statistics,
} satisfies Record<string, unknown>;

export default api;
