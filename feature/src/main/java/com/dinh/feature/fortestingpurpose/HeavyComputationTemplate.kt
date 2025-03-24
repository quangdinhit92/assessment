package com.dinh.feature.fortestingpurpose

import javax.inject.Inject

interface HeavyComputationTemplate {
    suspend fun doComputation(): String
}


class HeavyComputationTemplateImpl @Inject constructor():HeavyComputationTemplate
{
    override suspend fun doComputation(): String {
        TODO("Not yet implemented")
    }

}