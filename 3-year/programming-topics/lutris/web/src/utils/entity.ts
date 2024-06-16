import {
  ENTITIES_META,
  ENTITIES_TYPES,
  type EntityMeta,
  type TranslatedEntities,
} from "~/constants/entities";

export const isValidEntity = (entity: any): entity is TranslatedEntities =>
  Object.values(ENTITIES_TYPES).includes(entity);

export const getMetadata = (entity: TranslatedEntities) =>
  ENTITIES_META.find((e) => e.key === entity) as EntityMeta<string>;
