# movie

private Object getValue(Map<String, Object> levels, String key, Object result) {

        if (levels == null
                || (levels != null && levels.size() == 0)) {
            return null;
        }

        if (levels.get(key) != null) {
            result = levels.get(key);
        } else {

            for (String mapKey : levels.keySet()) {
                if (levels.get(key) == null) {
                    if (levels.get(mapKey) instanceof Map) {
                        result = getValue((Map<String, Object>) levels.get(mapKey), key, result);
                    } else if (levels.get(mapKey) instanceof List) {
                        List<Map> list = (List<Map>) levels.get(mapKey);
                        for (Map lhm2 : list) {
                            result = getValue((Map<String, Object>) lhm2, key, result);
                        }
                    }
                } else {
                    result = levels.get(key);
                }
            }
        }

        return result;
    }
