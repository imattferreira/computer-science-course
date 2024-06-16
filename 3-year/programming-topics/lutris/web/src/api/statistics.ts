import type { Entities } from "~/constants/entities";
import request from "~/utils/request";

export type StatisticsCounter = Record<Entities, number>;

const endpoints = {
  count: () => request.get<StatisticsCounter>("/statistics/count"),
};

export default endpoints;
