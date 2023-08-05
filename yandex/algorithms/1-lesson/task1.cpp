//Допустим, мы проводим онлайн-конкурс работ молодых художников. Всего представлено
//�
//N работ, которые идентифицируются числами от
//0
//0 до
//�
//−
//1
//N−1 включительно. Нужно поддержать 3 типа запроса:
//Лайк работы с идентификатором id.
//Дизлайк работы с идентификатором id.
//Вернуть лучшие
//�
//K работ. Оценку работы будем считать просто: число лайков минус число дизлайков.
//Для самого простого решения достаточно динамического массива и сортировки.

// 1 approach

class Competition {
    vector<int> scores;

    void change_score(size_t participant_id, int change) {
        scores[participant_id] += change;
    }

public:
    Competition(size_t participant_count) {
        scores.assign(participant_count, 0);
    }

    void like(size_t participant_id) {
        change_score(participant_id, 1);
    }

    void dislike(size_t participant_id) {
        change_score(participant_id, -1);
    }

    vector<size_t> get_best_works(size_t K) const {
        // Отсортируем работы в порядке убывания числа лайков
        vector<pair<int, size_t> > scores_ids;
        for (size_t i = 0; i < scores.size(); ++i)
            scores_ids.push_back({scores[i], i});
        sort(scores_ids.rbegin(), scores_ids.rend());

        // Теперь, когда работы отсортированы, выберем K лучших
        vector<size_t> result;
        for (size_t i = 0; i < min(K, scores_ids.size()); ++i)
            result.push_back(scores_ids[i].second);
        return result;
    }
};

// Можно предложить альтернативное решение со вспомогательной структурой данных.

class Competition {
    vector<int> scores;
    set<pair<int, size_t> > ordered_works;

    void change_score(size_t participant_id, int change) {
        ordered_works.erase({scores[participant_id], participant_id});
        scores[participant_id] += change;
        ordered_works.insert({scores[participant_id], participant_id});
    }

public:
    Competition(size_t participant_count) {
        scores.assign(participant_count, 0);
        for (size_t i = 0; i < participant_count; ++i)
            ordered_works.insert({0, i});
    }

    void like(size_t participant_id) {
        change_score(participant_id, 1);
    }

    void dislike(size_t participant_id) {
        change_score(participant_id, -1);
    }

    vector<size_t> get_best_works(size_t K) const {
        vector<size_t> result;
        set<pair<int, size_t> >::const_reverse_iterator it;
        for (it = ordered_works.rbegin();
             it != ordered_works.rend() && result.size() < K; ++it) {
            result.push_back(it->second);
        }
        return result;
    }
};